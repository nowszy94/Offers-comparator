/* jQuery function to add loading spinner for Excel with setTimeOut*/
$('#loading').on('click', function () {
    $(this).button('loading');
    setTimeout(function () {
        $("#loading").button('reset');
    }, 1000);
});

/* JS function to find cookie created in backend*/
var getCookie = function (name) {
    var cookies = document.cookie.split(';');
    for (var i = 0; i < cookies.length; ++i) {
        var pair = cookies[i].trim().split('=');
        if (pair[0] == name)
            return pair[1];
    }
    return null;
};
/* JS function to add loading spinner to PDF button*/
const loadAnimationButton = document.querySelector('#loading-pdf');
loadAnimationButton.addEventListener('click', function () {
    loadAnimationButton.innerHTML = "<i class='fa fa-spinner fa-spin'></i><span>Loading...</span>";
    alert('Początek pobierania');
    setTimeout(checkDownloadCookie, 1000);
});


let downloadTimeout;
const checkDownloadCookie = function () {
    if (getCookie("myCookie") == 1) {
        loadAnimationButton.innerHTML = "<i class='material-icons'>&#xe90d;</i><span>Print to PDF</span>";
    } else {
        downloadTimeout = setTimeout(checkDownloadCookie, 1000);
    }
};



/* jQuery autocomplete turn on */
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


