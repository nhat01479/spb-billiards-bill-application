page.dialogs.elements.formCreate.validate({
    rules: {
        productName: {
            required: true,
            minlength: 5,
            maxlength: 20,
        },
        productPrice: {
            required: true,
            number: true,
            maxlength: 7,
        },
        productUnit: {
            required: true,
            maxlength: 10,
        },
        productImage: {
            required: true
        }

    },
    messages: {
        productName: {
            required: 'Tên sản phẩm là bắt buộc',
            minlength: 'Tên sản phẩm ít nhất 5 ký tự',
            maxlength: "Tên sản phẩm tối đa 20 ký tự"
        },
        productPrice: {
            required: 'Giá là bắt buộc',
            number: 'Nhập giá bằng số',
            maxlength: 'Giá tối đa 7 chữ số',
        },
        productUnit: {
            required: 'Đơn vị tính là bắt buộc',
            maxlength: 'Tối đa 10 ký tự',
        },
        productImage: {
            required: 'Vui lòng chọn ảnh để upload!'
        }
    },
    errorLabelContainer: "#modalCreateProduct .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalCreateProduct .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaCreateProduct.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaCreateProduct.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaCreateProduct.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.createProduct();
    }
});
/****************************/
page.dialogs.elements.formUpdate.validate({
    rules: {
        productNameUp: {
            required: true,
            minlength: 5,
            maxlength: 20,
        },
        productPriceUp: {
            required: true,
            maxlength: 7,
        },
        productUnitUp: {
            required: true,
            maxlength: 10,
        },
        productImageUp: {
            required: true
        }

    },
    messages: {
        productNameUp: {
            required: 'Tên sản phẩm là bắt buộc',
            minlength: 'Tên sản phẩm ít nhất 5 ký tự',
            maxlength: "Tên sản phẩm tối đa 20 ký tự"
        },
        productPriceUp: {
            required: 'Giá là bắt buộc',
            maxlength: 'Giá tối đa 7 chữ số',
        },
        productUnitUp: {
            required: 'Đơn vị tính là bắt buộc',
            maxlength: 'Tối đa 10 ký tự',
        },
        productImageUp: {
            required: 'Vui lòng chọn ảnh để upload!'
        }
    },
    errorLabelContainer: "#modalUpdateProduct .error-area",
    errorPlacement: function (error, element) {
        error.appendTo("#modalUpdateProduct .error-area");
    },
    showErrors: function (errorMap, errorList) {
        if (this.numberOfInvalids() > 0) {
            page.dialogs.elements.errorAreaUpdate.removeClass("hide").addClass("show");
        } else {
            page.dialogs.elements.errorAreaUpdate.removeClass("show").addClass("hide").empty();
            page.dialogs.elements.errorAreaUpdate.removeClass("error");
        }
        this.defaultShowErrors();
    },
    submitHandler: function () {
        page.dialogs.commands.updateProduct();
    }
});
