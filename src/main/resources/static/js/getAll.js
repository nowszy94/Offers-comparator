
/* jQuery function to add loading spinner for Excel*/
$('#loading').on('click', function () {
    $(this).button('loading');
    setTimeout(function () {
        $("#loading").button('reset');
    }, 1000);
});


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


document.getElementById('pdf-href').addEventListener("click", function () {
    document.querySelector('.bg-modal').style.display = "flex";
});
document.getElementById('excel-href').addEventListener("click", function () {
    document.querySelector('.bg-modal').style.display = "flex";
});

document.querySelector('.close').addEventListener("click", function () {
    document.querySelector('.bg-modal').style.display = "none";
});



