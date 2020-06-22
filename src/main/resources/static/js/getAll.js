/* jQuery function to add loading spinner for Excel*/
$('#loading').on('click', function () {
    $(this).button('loading');
    setTimeout(function () {
        $("#loading").button('reset');
    }, 1000);
});

/* jQuery function to add loading spinner for PDF*/
// $('#loading-pdf').on('click', function () {
//     $(this).button('loading');
//     setTimeout(function () {
//         $("#loading-pdf").button('reset');
//     }, 10000);
// });


/* Setting cookies for downloading pdf*/
const setCookie = function (name, value, expiracy) {
    const exdate = new Date();
    exdate.setTime(exdate.getTime() + expiracy * 1000);
    const c_value = escape(value) + ((expiracy == null) ? "" : "; expires=" + exdate.toUTCString());
    document.cookie = name + "=" + c_value + '; path=/';
};

const getCookie = function (name) {
    var i, x, y, ARRcookies = document.cookie.split(";");
    for (i = 0; i < ARRcookies.length; i++) {
        x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
        y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
        x = x.replace(/^\s+|\s+$/g, "");
        if (x == name) {
            return y ? decodeURI(unescape(y.replace(/\+/g, ' '))) : y; //;//unescape(decodeURI(y));
        }
    }
};

$('#loading-pdf').on('click', function () {
    $(this).button('loading');
    setCookie('downloadStarted', 0, 100); //Expiration could be anything... As long as we reset the value
    setTimeout(checkDownloadCookie, 1000);
});


let downloadTimeout;
const checkDownloadCookie = function () {
    if (getCookie("downloadStarted") == 1) {
        console.log('This is a message from cookie = 1');
        setCookie("downloadStarted", 0, 100); // false,   Expiration could be anything... As long as we reset the value
        $("#loading-pdf").button('reset');
    } else {
        downloadTimeout = setTimeout(checkDownloadCookie, 1000); //Re-run this function in 1 second.
    }
};



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



