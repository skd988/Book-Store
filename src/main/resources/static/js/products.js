'use strict';

const keepInputs = document.currentScript.getAttribute('keepInputs') === "true";

/**
 * Script to handle form errors, changing to update form and reset form.
 */
(() => {
    const ADD_FORM_TITLE = 'Add Product';
    const UPDATE_FORM_TITLE = 'Update Product';
    const ADD_URL = '/admin/products/add';
    const UPDATE_BASE_URL = '/admin/products/update/';
    const GET_PRODUCT_BASE_URL = "/admin/products/get/";
    const UPDATE_ID_BASE_MSG = 'Update to product #';
    const FETCH_FAILED_MSG = 'Error: Refresh the page and try again';

    /**
     *  status check for a response. if response is 2xx it is accepted, otherwise rejected (returns text and status object)
     * @param response for status check
     * @returns fetch promise, resolved or rejected
     **/
    const status = (response) => {
        return (response.status >= 200 && response.status < 300) ?
            Promise.resolve(response) : Promise.reject({statusText: response.statusText, status: response.status});
    };


    window.addEventListener('DOMContentLoaded', () => {
        const form = document.getElementById('form');
        const form_title = form.firstElementChild;
        const update_buttons = document.getElementsByClassName('update-btn');
        const update_to_msg = document.getElementById('update-id');
        const error_msg = document.getElementById('error-msg');
        const inputs = [...form.getElementsByClassName('input')];

        /**
         * Updates the general error message with a new error.
         * @param msg Message to present.
         */
        const showErrorMsg = (msg) => {
            error_msg.innerText = msg;
        };

        /**
         * Hides the general error message.
         */
        const hideErrorMsg = () => {
            error_msg.innerText = '';
        };

        /**
         * Resets form inputs (back to empty).
         */
        const resetInputs = () => {
            inputs.forEach(input => input.value = '');
        };

        /**
         * Hides all form errors.
         */
        const hideFormErrors = () => {
            [...document.getElementsByClassName('form-error')].forEach(elem => elem.innerHTML = '');
        }

        /**
         * Function to be called on the event of clicking on an update button of one of the products.
         * Updates the form to an update-form, loads the product details to the form and allows the user
         * to update the product's members. When clicking submit instead of adding a new entry
         * it will alter the to-update product.
         * @param event Event of the click on the update button.
         */
        const changeToUpdateForm = (event) => {
            const id = event.target.dataset.id;
            fetch(GET_PRODUCT_BASE_URL + event.target.dataset.id)
                .then(status)
                .then(res => res.json())
                .then(product => {
                    hideFormErrors();
                    form_title.innerText = UPDATE_FORM_TITLE;

                    form.setAttribute('action', UPDATE_BASE_URL + id);
                    update_to_msg.innerText = UPDATE_ID_BASE_MSG + id;

                    delete product.id;
                    for(const [key, value] of Object.entries(product)) {
                        const input = inputs.find(input => input.getAttribute('name') === key)
                        if(input)
                            input.value = value;
                    }
                })
                .catch(e => {
                    showErrorMsg(FETCH_FAILED_MSG);
                })
        };

        /**
         * Resets the form - removes all inputs, hides all form errors, and returns it to "add-product" form.
         * @param event Event of reset the form.
         */
        const resetForm = (event) => {
            event?.preventDefault();
            resetInputs();
            update_to_msg.innerText = '';
            form_title.innerText = ADD_FORM_TITLE;
            form.setAttribute('action', ADD_URL);
            hideFormErrors();
        };

        if(!keepInputs)
            resetForm();

        //add listeners to all update buttons
        [].forEach.call(update_buttons, (button) => {
            button.addEventListener('click', changeToUpdateForm)
        });

        //add listener to the reset event of the form
        form.addEventListener('reset', resetForm);
    });
})();