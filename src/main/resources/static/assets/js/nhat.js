page.elements.btnShowCreateModal = $('#btnShowCreateModal');
page.elements.tbProduct = $('#tbProduct tbody');

// Create
page.dialogs.elements.modalCreateProduct = $('#modalCreateProduct');
page.dialogs.elements.formCreate = $('#formCreate')
page.dialogs.elements.errorAreaCreate = $('#modalCreateProduct .error-area')

page.dialogs.elements.productName = $('#productName')
page.dialogs.elements.productPrice = $('#productPrice')
page.dialogs.elements.productUnit = $('#productUnit')
page.dialogs.elements.productCategory = $('#productCategory')
page.dialogs.elements.productDescription = $('#productDescription')
page.dialogs.elements.productImage = $('#productImage')

page.dialogs.elements.btnCreateProduct = $('#btnCreateProduct');
page.dialogs.elements.imagePreview = $('#image-preview');

// Update
page.dialogs.elements.modalUpdateProduct = $('#modalUpdateProduct')
page.dialogs.elements.formUpdate = $('#formUpdate')
page.dialogs.elements.errorAreaCreate = $('#modalUpdateProduct .error-area')

page.dialogs.elements.productNameUp = $('#productNameUp')
page.dialogs.elements.productPriceUp = $('#productPriceUp')
page.dialogs.elements.productUnitUp = $('#productUnitUp')
page.dialogs.elements.productCategoryUp = $('#productCategoryUp')
page.dialogs.elements.productDescriptionUp = $('#productDescriptionUp')
page.dialogs.elements.productImageUp = $('#productImageUp')

page.dialogs.elements.btnUpdateProduct = $('#btnUpdateProduct');
page.dialogs.elements.imagePreviewUp = $('#image-preview-up');
page.dialogs.elements.btnChaneImg = $('#btnChaneImg');



let productId = 0;
let product = null;

page.commands.renderProduct = (product) => {
    const avatar = product.avatar;
    return `<tr id="tr_${product.id}">
                    <td style="max-width: 20px;">${product.id}</td>
                    <td style="max-width: 150px; text-align: center">
                        <img src="${avatar.fileUrl}" style="width: 40%; aspect-ratio: 3/2; object-fit: contain;">
                    </td>
                    <td>${product.title}</td>
                    <td class="pl-5">${product.price}</td>
                    <td>${product.description}</td>
                    <td class="text-center">${product.category.code}</td>
                    <td>
                        <div class="d-flex justify-content-around">
                            <button class="btn btn-warning btn-sm" data-id="${product.id}">
                                <i class="fas fa-eye"></i></button>
                            <button class="btn btn-secondary btn-sm edit" data-id="${product.id}">
                                <i class="fas fa-edit"></i></button>
                            <button  class="btn btn-lighten-danger btn-sm delete" data-id="${product.id}">
                                <i class="fas fa-trash-alt"></i></button>
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
                page.elements.tbProduct.append(str);
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

        page.dialogs.elements.productCategoryUp.val(product.category.id);
        page.dialogs.elements.productCategoryUp.text(product.category.code);

        page.dialogs.elements.productDescriptionUp.val(product.description);
        page.dialogs.elements.imagePreviewUp.attr('src', product.avatar.fileUrl);
        page.commands.getAllCategory(page.dialogs.elements.productCategoryUp).then((data) => {
            const options = page.dialogs.elements.productCategoryUp.find('option');
            $.each((options), (index, item) => {
                console.log(item.value);
                console.log(product.category.id);
                const a = (item.value === product.category.id);
                console.log(a)
                // if (item.value === product.category.id) {
                //
                // }
            })
        });



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
            // const responseJSON = jqXHR.responseJSON;
            //
            // page.dialogs.elements.errorAreaUpdate.empty();
            // let str = '';
            //
            // $.each(responseJSON, (k, v) => {
            //     str += `<label for="${k}Update">${v}</label>`
            // })
            //
            // page.dialogs.elements.errorAreaUpdate.append(str).removeClass('hide').addClass('show');
            //
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
        page.dialogs.commands.createProduct()
    })

    page.dialogs.elements.btnUpdateProduct.on('click', () => {
        page.dialogs.commands.updateProduct();
    })

    // page.elements.uploadArea.on('click', () => {
    //     page.dialogs.elements.productImage.trigger('click');
    // })


    page.dialogs.elements.btnChaneImg.on('click', () => {
        page.dialogs.elements.productImageUp.trigger('click');
    });

    page.dialogs.elements.productImage.on('change', () => {
        // console.log(page.dialogs.elements.productImage[0].size())
        const avatar = URL.createObjectURL(page.dialogs.elements.productImage[0].files[0]);
        page.dialogs.elements.imagePreview.attr('src', avatar)
    })

}





page.loadData = () => {
    page.commands.getAllProduct()
}


$(() => {
    page.loadData();

    page.initializeControlEvent();
})
