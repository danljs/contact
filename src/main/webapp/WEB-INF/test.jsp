<!DOCTYPE html>
<html ng-app="app">
<head>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
</head>
<body>
	<div ng-controller="conCtrl">
		<div class="row" style="padding:0 300px 0 300px">
			<div class="row">
				<div class="form-group col-sm-12" style="padding:10px 0 0 20px;">
					<a class="btn btn-success btn-bg" role="button" ng-click="getcontacts()">Contacts</a>
					<a class="btn btn-success btn-bg" role="button" ng-click="save()">Save</a>
					<a class="btn btn-success btn-bg" role="button" ng-click="search()">Search</a>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-2">ID</div>
				<div class="form-group col-sm-10" >
					<input type="text" ng-model="contact.id" class="form-control"></input>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-2">Name</div>
				<div class="form-group col-sm-10" >
					<input type="text" ng-model="contact.name" class="form-control"></input>
				</div>
			</div>	
			<div class="row">
				<div class="form-group col-sm-2">Email</div>
				<div class="form-group col-sm-10" >
					<input type="email" ng-model="contact.email" class="form-control" placeholder="xxxxx.xxx@gmail.com"></input>
				</div>
			</div>
			<div ng-show="isShow">
				<div class="row">
					<h5 style="margin: 40px 0;">
					<div class="form-group col-sm-2">ID</div>
					<div class="form-group col-sm-5">NAME</div>
					<div class="form-group col-sm-5">EMAIL</div>
					</h5>
				</div>
				<div class="row" ng-repeat="x in contacts" >
					<a><div class="form-group col-sm-2">{{ x.id }}</div>
					<div class="form-group col-sm-5">{{ x.name }}</div>
					<div class="form-group col-sm-5">{{ x.email }}</div></a>
				</div>
			</div>
		</div>
		<h5>{{msg}}</h5>	
		<h5 style="margin: 10px 0;color:rgba(0,0,0,0)">&nbsp;</h5>	
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.5/angular.js"></script>
    <script>
	angular.module('app', []);
    angular.module('app').factory('model', ['$http', function ($http) {
		var host = "http://localhost:8080/contact/";
        var contact = {
			id:'',
			name:'',
			email:''
        };

        function resetContact() {
            contact.id = '',
            contact.name = '',
            contact.email = ''
        };

        function saveContact(contact) {
			alert("Contact saved.");
            var op = {
				id: contact.id,
				name: contact.name,
				email: contact.email
            }
            
            $http({
                url: host + "saveContact/",
                method: "POST",
                data: { contact: JSON.stringify(op) }
            }).then(
                function (response) {
                    var json = response.data;
                    if (json.Status) {
                        alert("Your record saved successful!")
                        resetContact();
                    } else {
                        alert("Unsuccessful save record!")
                    }
                },
                function (e) {
                    alert("Error to save!")
                }
            );
        };

        function getContacts() {
            $http({
                url: host + "getContacts/",
                method: "GET",
                data: { id: ""}
            }).then(
                function (response) {
                    var json = response.data;
                    if (json.Status) {
                        ;
                    } else {
                        alert("Unsuccessful save record!")
                    }
                },
                function (e) {
                    alert("Error to get Contacts!")
                }
            );
		}
        
        function searchContact() {
            var id = contact.id;
  
            $http({
                url: host + "searchContact/",
                method: "GET",
                data: { id: contact.id }
            }).then(
                function (response) {
                    var json = response.data;
                    if (json.Status) {
                        alert(json);
                    } else {
                        alert("Unsuccessful save record!")
                    }
                },
                function (e) {
                    alert("Error to contact!")
                }
            );
		}
			
        var ret = {
            getbtn: function() {
            	getContacts();
            },
			
            savebtn: function (contact) {
                saveContact(contact);
            },
			
            searchbtn: function() {
            	alert(0);
            	getContacts();
            }
        };
        return ret;
    }]);
	
    angular.module('app').controller('conCtrl', ['$scope', 'model', function ($scope, model) {
		$scope.isShow = false;
		
        $scope.save = function () {
            $scope.msg = "";
            if (!!!$scope.contact.id || !!!$scope.contact.name ||
                !!!$scope.contact.email) {
                $scope.msg = "Please fill out required fields!";
                return;
            }
            model.savebtn($scope.contact);
        };
        $scope.getcontacts = function () {
            model.getbtn();
            $scope.isShow = true;
        };
				
		$scope.search = function () {
            model.searchbtn();
        };

    }]);    
    </script>
  </body>
</html>      