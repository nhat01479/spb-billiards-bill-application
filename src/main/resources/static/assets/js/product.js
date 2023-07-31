

const page = {
    url: {},
    elements: {},
    loadData: {},
    commands: {},
    dialogs: {
        elements: {},
        commands: {}
    },
    initializeControlEvent: {}
}

// url: /page=pageable.page&

page.elements.btnShowCreateModal = $('#btnShowCreateModal');
page.elements.tbProduct = $('#tbProduct tbody');

// Create
page.dialogs.elements.modalCreateProduct = $('#modalCreateProduct');
page.dialogs.elements.formCreate = $('#formCreate')
page.dialogs.elements.errorAreaCreateProduct = $('#modalCreateProduct .error-area')

page.dialogs.elements.productName = $('#productName')
page.dialogs.elements.productPrice = $('#productPrice')
page.dialogs.elements.productUnit = $('#productUnit')
page.dialogs.elements.productCategory = $('#productCategory')
page.dialogs.elements.productDescription = $('#productDescription')
page.dialogs.elements.productImage = $('#productImage')

page.dialogs.elements.btnCreateProduct = $('#btnCreateProduct');
page.dialogs.elements.imagePreview = $('#image-preview');
page.dialogs.elements.btnChooseImg = $('#btnChooseImg');

// Update
page.dialogs.elements.modalUpdateProduct = $('#modalUpdateProduct')
page.dialogs.elements.formUpdate = $('#formUpdate')
page.dialogs.elements.errorAreaUpdate = $('#modalUpdateProduct .error-area')

page.dialogs.elements.productNameUp = $('#productNameUp')
page.dialogs.elements.productPriceUp = $('#productPriceUp')
page.dialogs.elements.productUnitUp = $('#productUnitUp')
page.dialogs.elements.productCategoryUp = $('#productCategoryUp')
page.dialogs.elements.productDescriptionUp = $('#productDescriptionUp')
page.dialogs.elements.productImageUp = $('#productImageUp')

page.dialogs.elements.btnUpdateProduct = $('#btnUpdateProduct');
page.dialogs.elements.imagePreviewUp = $('#image-preview-up');
page.dialogs.elements.btnChaneImg = $('#btnChaneImg');

page.elements.sortByPrice = $('#sortByPrice');
page.elements.keySearch = $('#keySearch');


page.commands.renderProduct = (product) => {
    const avatar = product.avatar;

    const fileName = avatar.fileName;
    const fileFolder = avatar.fileFolder;
    const  imgUrl = App.API_CLOUD_IMAGE + '/' + App.BASE_SCALE_IMAGE + '/' + fileFolder + '/' + fileName;

    return `<tr id="tr_${product.id}">
                    <td class="text-center">${product.id}</td>
                    <td style="max-width: 150px; text-align: center">
                        <img src="${imgUrl}" style="width: 40%; aspect-ratio: 3/2; object-fit: contain;">
                    </td>
                    <td>${product.title}</td>
                    <td class="text-end ">${formatCurrency(product.price)}</td>
                    <td class="text-center">${product.description}</td>
                    <td class="text-center">${product.category.code}</td>
                    <td>
                        <div class="d-flex justify-content-center">                          
                            <button class="btn btn-outline-secondary btn-sm edit" data-id="${product.id}">
                                <i class="fas fa-edit"></i></button>
                            <button  class="btn btn-outline-danger btn-sm delete" data-id="${product.id}">
                                <i class="fas fa-ban"></i></button>
                        </div>
                    </td>
                </tr>`;
}
page.commands.renderCategory = (category) => {
    return `
            <option value="${category.id}">(${category.id}) ${category.code}</option>
        `;
}
page.commands.getAllCategory = (element) => {
    return $.ajax({
        type: 'GET',
        url: "http://localhost:28002/api/products/get-all-category",
    })
        .done((data) => {
            element.empty();


            data.forEach((item) => {
                const str = page.commands.renderCategory(item);
                // const str = `<option value="${item.id}">(${item.id}) ${item.code}</option>`
                element.append(str);
            });
        })
        .fail((error) => {
            console.log(error);
        })
}

page.commands.getAllProduct = () => {
    page.elements.tbProduct.empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:28002/api/products'
    })
        .done((data) => {
            data.forEach(item => {

                const str = page.commands.renderProduct(item);
                page.elements.tbProduct.prepend(str);
            });
        })
        .fail((error) => {
            console.log(error);
        })
}

page.commands.getAllProductByKeySearch = (keySearch) => {
    page.elements.tbProduct.empty();
    $.ajax({
            type: 'GET',
        url: 'http://localhost:28002/api/products/search',
        data: {
            keySearch: keySearch
        }
    })

        .done((data) => {
            page.elements.tbProduct.empty();
            data.forEach(item => {

                const str = page.commands.renderProduct(item);
                page.elements.tbProduct.prepend(str);
            });
        })
        .fail((error) => {
            console.log()
            console.log(error);
        })
}
page.commands.getAllProductSorted = (sortBy, direction) => {
    page.elements.tbProduct.empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:28002/api/products/sorted?sort_by=' + sortBy + '&direction=' + direction
    })
        .done((data) => {
            data.forEach(item => {

                const str = page.commands.renderProduct(item);
                page.elements.tbProduct.prepend(str);
            });
        })
        .fail((error) => {
            console.log(error);
        })
}
page.commands.getProductById = (id) => {
    return $.ajax({
        type: 'GET',
        url: 'http://localhost:28002/api/products/' + id,
    })
}

page.commands.handleShowModalUpdateProduct = (productId) => {
    page.commands.getProductById(productId).then((data) => {
        product = data;

        page.dialogs.elements.productNameUp.val(product.title);
        page.dialogs.elements.productPriceUp.val(product.price);
        page.dialogs.elements.productUnitUp.val(product.unit);


        page.dialogs.elements.productDescriptionUp.val(product.description);
        page.dialogs.elements.imagePreviewUp.attr('src', product.avatar.fileUrl);
        page.commands.getAllCategory(page.dialogs.elements.productCategoryUp).then((data) => {
            page.dialogs.elements.productCategoryUp.val(product.category.id)
            // const options = page.dialogs.elements.productCategoryUp.find("option");
            // $.each((options), (index, item) => {
            //
            //     if (+$(item).val() === product.category.id) {
            //
            //         $(item).attr("selected", "selected")
            //     }
            // })
        })


        page.dialogs.elements.modalUpdateProduct.modal("show");

    })
        .catch((error) => {
        console.log(error);
    });
}



page.dialogs.commands.createProduct = () => {
    const title = page.dialogs.elements.productName.val();
    const price = page.dialogs.elements.productPrice.val();
    const unit = page.dialogs.elements.productUnit.val();
    const categoryId = page.dialogs.elements.productCategory.val();
    const description = page.dialogs.elements.productDescription.val();
    const avatar = page.dialogs.elements.productImage[0].files[0];

    const formData = new FormData();
    formData.append("title", title);
    formData.append("price", price);
    formData.append("unit", unit);
    formData.append("categoryId", categoryId);
    formData.append("description", description);
    formData.append("avatar", avatar);
    $.ajax({
        url: "http://localhost:28002/api/products",
        type: "POST",
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
    })
        .done((data) => {
            const str = page.commands.renderProduct(data);
            page.elements.tbProduct.prepend(str);

            page.dialogs.elements.modalCreateProduct.modal('hide');

            App.showSuccessAlert('Thêm sản phẩm thành công');
        })
        .fail((jqXHR) => {
            const responseJSON = jqXHR.responseJSON;

            page.dialogs.elements.errorAreaCreateProduct.empty();
            let str = '';

            $.each(responseJSON, (k, v) => {
                str += `<label for="${k}">${v}</label>`
            })

            page.dialogs.elements.errorAreaCreateProduct.append(str).removeClass('hide').addClass('show');

            console.log(jqXHR);
        })
}
page.dialogs.commands.updateProduct = () => {
    const title = page.dialogs.elements.productNameUp.val();
    const price = page.dialogs.elements.productPriceUp.val();
    const unit = page.dialogs.elements.productUnitUp.val();
    const categoryId = page.dialogs.elements.productCategoryUp.val();
    const description = page.dialogs.elements.productDescriptionUp.val();
    const avatar = page.dialogs.elements.productImageUp[0].files[0];


    const formData = new FormData();
    formData.append("title", title);
    formData.append("price", price);
    formData.append("unit", unit);
    formData.append("categoryId", categoryId);
    formData.append("description", description);
    formData.append("avatar", avatar);
    $.ajax({
        type: "PATCH",
        url: "http://localhost:28002/api/products/" + productId,
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
    })
        .done((data) => {
            const str = page.commands.renderProduct(data);
            const currentRow = $('#tr_' + productId);
            currentRow.replaceWith(str);
            page.dialogs.elements.modalUpdateProduct.modal('hide');

            App.showSuccessAlert('Sửa sản phẩm thành công');
        })
        .fail((jqXHR) => {
            const responseJSON = jqXHR.responseJSON;

            page.dialogs.elements.errorAreaUpdate.empty();
            let str = '';

            $.each(responseJSON, (k, v) => {
                str += `<label for="${k}">${v}</label>`
            })

            page.dialogs.elements.errorAreaUpdate.append(str).removeClass('hide').addClass('show');

            console.log(jqXHR);
        })
}
page.commands.handleDeleteCustomer = (productId) => {
    App.showDeleteConfirmDialog().then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:28002/api/products/delete/' + productId,
                data: {
                    deleted: 1
                }
            })
                .done(() => {
                    $('#tr_' + productId).remove();
                    App.showSuccessAlert('Xoá thành công');
                })
                .fail((jqXHR) => {
                    console.log(jqXHR);
                    const httpStatus = jqXHR.status;
                    if (httpStatus === 403) {
                        App.showErrorAlert('Bạn không đủ quyền để thực hiện chức năng này');
                    }
                    if (httpStatus === 401) {
                        App.showErrorAlert('Vui lòng đăng nhập, tài khoản của bạn đã hết phiên làm việc');
                    }
                })
        }
    })
}

page.initializeControlEvent = () => {
    page.elements.btnShowCreateModal.on('click', function () {
        page.dialogs.elements.modalCreateProduct.modal("show");
        page.commands.getAllCategory(page.dialogs.elements.productCategory);
    })
    page.elements.tbProduct.on('click', '.delete', function () {
        productId =  $(this).data('id');
        page.commands.handleDeleteCustomer(productId);
    })
    page.elements.tbProduct.on('click', '.edit', function () {
        productId =  $(this).data('id');
        page.commands.handleShowModalUpdateProduct(productId);

    })



    page.dialogs.elements.btnCreateProduct.on('click', () => {
        page.dialogs.elements.formCreate.trigger("submit");
        // page.dialogs.commands.createProduct()
    })

    page.dialogs.elements.btnUpdateProduct.on('click', () => {
        page.dialogs.elements.formUpdate.trigger("submit");
        // page.dialogs.commands.updateProduct();
    })


    page.dialogs.elements.btnChooseImg.on('click', () => {
        page.dialogs.elements.productImage.trigger('click');

    });
    page.dialogs.elements.productImage.on('change', () => {
        const avatar = URL.createObjectURL(page.dialogs.elements.productImage[0].files[0]);
        page.dialogs.elements.imagePreview.attr('src', avatar);
        const file = page.dialogs.elements.productImage[0].files[0];
        console.log(file)
        if (file) {
            $('#btnChooseImg').text("Change Image");
        } else {
            $('#btnChooseImg').text("Chọn ảnh để upload");
        }
    })

    page.dialogs.elements.btnChaneImg.on('click', () => {
        page.dialogs.elements.productImageUp.trigger('click');
    });
    page.dialogs.elements.productImageUp.on('change', () => {
        const avatar = URL.createObjectURL(page.dialogs.elements.productImageUp[0].files[0]);
        page.dialogs.elements.imagePreviewUp.attr('src', avatar)
    })

    page.elements.keySearch.on('input', function () {
        const keySearch = $(this).val();
        // $('#formSearch').trigger("submit");
        page.commands.getAllProductByKeySearch(keySearch);

    })

    page.elements.sortByPrice.on('click', function () {
         if ($(this).hasClass('asc')) {
             $(this).addClass('desc').removeClass('asc');
         } else {
             if ($(this).hasClass('desc')) {
                 $(this).addClass('asc').removeClass('desc');
             }
         }
        const direction = $(this).hasClass('asc') ? 'asc' : 'desc';
        const sortBy = 'price';
        page.commands.getAllProductSorted(sortBy, direction);
        // pageable.dimension = pageable.dimension === "asc" ? 'des' : 'asc';
        //
        // page.commands.getAllProductPage(pageable)

    })

}



page.loadData = () => {
    page.commands.getAllProduct()

}


$(() => {
    page.loadData();

    page.initializeControlEvent();
})


