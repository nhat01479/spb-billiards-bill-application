class App {

    static DOMAIN_SERVER = window.origin;
    static API_SERVER = this.DOMAIN_SERVER + '/api';

    static API_PRODUCT = this.API_SERVER + '/customers';
    static API_DESK = "http://localhost:28002/api/desks";
    static API_TYPE = this.API_SERVER + '/types';
    static API_ORDER = this.API_SERVER;
    static API_PRODUCT_ORDER_ITEM = this.API_SERVER;
    static API_TIME_ORDER_ITEM = this.API_SERVER;
    static API_USER = this.API_SERVER + '/users';


    static API_LOCATION_REGION = 'https://vapi.vnappmob.com/api/province'

    static API_CLOUD_IMAGE = "https://res.cloudinary.com/dzq57irpw/image/upload";
    static API_CLOUD_VIDEO = "https://res.cloudinary.com/dzq57irpw/video/upload";
    static BASE_SCALE_IMAGE = "c_limit,w_150,h_100,q_100";
    static BASE_SCALE_IMAGE_HOME = "c_limit,w_177,h_132,q_100";
    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure you want to delete the selected data ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it !',
            cancelButtonText: 'Cancel',
        });
    }
    static showCheckoutConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Xác nhận thanh toán?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Xác nhận',
            cancelButtonText: 'Trở lại',
        });
    }

    static showSuccessAlert(t) {
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: t,
            showConfirmButton: true,
            timer: 1500
        })

    }

    static showErrorAlert(t) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: 'Warning',
            text: t,
            showConfirmButton: true,
        });
    }


}
 function formatCurrency(number){
    return number.toLocaleString('vi-VN', {style : 'currency', currency : 'VND'});
}
function convertCurrencyToNumber(currencyString) {
    const numericString = currencyString.replace(/,/g, '');

    const numberValue = parseFloat(numericString);

    return numberValue;
}

class Product {
    constructor() {
    }
}

class Desk {
    constructor() {
    }
}

class User {
    constructor() {

    }

}

class Staff {
    constructor() {

    }
}

$(function () {
    $(".num-space").number(true, 0, ',', ' ');
    $(".num-point").number(true, 0, ',', '.');
    $(".num-comma").number(true, 0, ',', ',');

    $('[data-toggle="tooltip"]').tooltip();
});
