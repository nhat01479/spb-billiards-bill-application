package com.cg.service.product;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpReqDTO;
import com.cg.repository.ProductAvatarRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.category.ICategoryService;
import com.cg.service.upload.IUploadService;

import com.cg.ultis.AppUtils;
import com.cg.ultis.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private AppUtils appUtils;


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public Boolean existsByTitle(String title) {
        return productRepository.existsByTitle(title);
    }

    @Override
    public Boolean existsByTitleAndIdNot(String title, Long id) {
        return productRepository.existsByTitleAndIdNot(title, id);
    }

    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public List<ProductDTO> findAllByDeletedFalse(Sort sort) {
        return productRepository.findAllByDeletedFalse(sort);
    }
    public List<ProductDTO> findAllByDeletedFalseAndTitleLikeAndDescriptionLike(String title, String description) {
        return productRepository.findAllByDeletedFalseAndTitleLikeAndDescriptionLike(title, description);

    }

    public Page<ProductDTO> findAllByDeletedFalse(Pageable pageable) {
        return productRepository.findAllByDeletedFalse(pageable);
    }
    @Override
    public List<ProductDTO> findAllProductDTOByCategoryId(Long categoryId) {
        return productRepository.findAllProductDTOByCategoryId(categoryId);
    }

    @Override
    public List<ProductDTO> findAllByDeletedFalseAndCategoryId(Long categoryId) {
        return productRepository.findAllByDeletedFalseAndCategoryId(categoryId);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductDTO create(ProductCreReqDTO productCreReqDTO) {

        Category category = categoryService.findById(productCreReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục sản phẩm không tồn tại");
        });

        String title = productCreReqDTO.getTitle();
        String titleChar = appUtils.replaceNonEnglishChar(title);
        String slug = appUtils.removeNonAlphanumeric(titleChar);

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        uploadAndSaveProductImage(productCreReqDTO, productAvatar);

        Product product = productCreReqDTO.toProduct(slug, category, productAvatar);
        productRepository.save(product);

        ProductDTO productDTO = product.toProductDTO();
        return productDTO;
    }

    @Override
    public ProductDTO update(ProductUpReqDTO productUpReqDTO, Product product) {

        Category category = categoryService.findById(productUpReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục sản phẩm không tồn tại");
        });

        String title = productUpReqDTO.getTitle();
        String titleChar = appUtils.replaceNonEnglishChar(title);
        String slug = appUtils.removeNonAlphanumeric(titleChar);

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        uploadAndSaveProductImage(productUpReqDTO, productAvatar);

        Product productUpdate = productUpReqDTO.toProduct(product, slug, category, productAvatar);
        productRepository.save(productUpdate);

        ProductDTO productDTO = productUpdate.toProductDTO();
        return productDTO;
    }

    private void uploadAndSaveProductImage(ProductCreReqDTO productCreReqDTO, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(productCreReqDTO.getAvatar(), uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }
    private void uploadAndSaveProductImage(ProductUpReqDTO productUpReqDTO, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(productUpReqDTO.getAvatar(), uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }
    @Override
    public void softDelete(Product product) {
        product.setDeleted(true);
        productRepository.save(product);
    }

}


