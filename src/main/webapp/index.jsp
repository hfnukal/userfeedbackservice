<html>
    <head>
        <title>User feedback service</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            function updateFeedbacks() {
                $.ajax({
                    type: 'GET',
                    url: "userfeedback/rest/feedback",
                    success: function (data) {
                        var json = eval(data);
                        $("#accordion").empty();
                        for (i in json) {(function(i){
                            $.ajax({
                                type: 'GET',
                                url:"userfeedback/rest/feedback/"+i,
                                success: function (udata) {
                                    var user = eval(udata);
                                    $("#accordion").append("<h3>["+i+"] "+user.name+"</h3><div>"+user.content+"</div>");
                                    $("#accordion").accordion( "refresh" );
                                }
                            });    
                        })(i);}
                    }
                });
            }
            
            function submitUser() {
                var name = $("#input-name").val();
                var content = $("#input-content").val();
                var udata={ name: name, content: content };
                $.ajax({
                    type:"POST",
                    url: "userfeedback/rest/feedback",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(udata),
                    success: function (data) {
                        updateFeedbacks();
                    }
                });
            }
            
            $(function () {
                $("#accordion").accordion();
            });
       
        </script>
    </head>
    <body>
        <h2>User feedback service</h2>
        <p>This REST service will receive user feedback and provide list of previous entries.</p>

        <li><code>userfeedback/rest/feedback</code> GET List of entries</li>
        <li><code>userfeedback/rest/feedback/{id}</code> GET Feedback details</li>
        <li><code>userfeedback/rest/feedback</code> POST Feedback details, accepting parameters <i>name</i> and <i>content</i></li>

        <p>All in JSON format<p>

        <button onclick="updateFeedbacks();">Update list</button>
        <div>
            Name: <input type="text" id="input-name"> Content: <input type="text" id="input-content"> <button onclick="submitUser();">Add user</button>
        </div>

        <div id="accordion">
            <h3>NO DATA</h3>
            <div>no data</div>
        </div>
    </body>
</html>
