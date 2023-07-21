class App {

    static DOMAIN_SERVER = window.origin;
    static API_SERVER = this.DOMAIN_SERVER + '/api';

    static API_PRODUCT = this.API_SERVER + '/customers';
    static API_USER = this.API_SERVER;
    static API_DESK = "http://localhost:28002/api/desks";
    static API_TYPE = this.API_SERVER + '/types';
    static API_STAFF = this.API_SERVER;
    static API_ORDER = this.API_SERVER;
    static API_PRODUCT_ORDER_ITEM = this.API_SERVER;
    static API_TIME_ORDER_ITEM = this.API_SERVER;
    static API_STAFF = this.API_SERVER + '/staffs';


    static API_LOCATION_REGION = 'https://vapi.vnappmob.com/api/province'

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

    static showSuccessAlert(t) {
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: t,
            showConfirmButton: true,
            // timer: 1500
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
