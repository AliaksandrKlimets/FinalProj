function getSecondTime(){
    var start = document.getElementById("serviceStart").value;
    var date = new Date(start);
    var yyyy = date.getFullYear();
    var mm = date.getMonth();
    var dd  = date.getDate();

    var date = new Date(yyyy,mm,dd+1);

    var yyyy = date.getFullYear();
    var mm = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1); // getMonth() is zero-based
    var dd  = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    document.getElementById("serviceEnd").min =yyyy+'-'+mm+'-'+dd;
}


function getNow() {
    var date = new Date();
    var yyyy = date.getFullYear();
    var mm = date.getMonth();
    var dd = date.getDate();

    var date = new Date(yyyy, mm, dd + 1);

    var yyyy = date.getFullYear();
    var mm = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1); // getMonth() is zero-based
    var dd = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    document.getElementById("serviceStart").min = yyyy + '-' + mm + '-' + dd;
}

function getMax(){
    var date = new Date();
    var yyyy = date.getFullYear();
    var mm = date.getMonth();
    var dd  = date.getDate();

    var date = new Date(yyyy-20,mm,dd);

    var yyyy = date.getFullYear();
    var mm = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1); // getMonth() is zero-based
    var dd  = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    document.getElementById("max").max=yyyy+'-'+mm+'-'+dd;
}