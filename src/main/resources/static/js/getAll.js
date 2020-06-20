$(document).ready(function () {

    $('#autocomplete-input').devbridgeAutocomplete({
        serviceUrl: '/suggestion',
        paramName: 'userSearch',
        minChars: 3,
        autoSelectFirst: true,
    });

});
$(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


