$(document).ready(function() {
    $.ajax({
        //url: "http://jsonplaceholder.typicode.com/users"
    	url: "http://localhost:8080/v1/demo/users"
    }).then(function(data) {
        // for debug
        //console.log(data);
        for (var i = 0; i < data.length; i++) {
            //console.log("id : " + data[i].id);
            //console.log("name : " + data[i].name);
            //console.log("username : " + data[i].username);

            // add rows
            $('.users').append(data[i].id + ", "
                + data[i].name + ", "
                + data[i].username + "<br>\n");
        }
    });
});