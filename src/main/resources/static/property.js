angular.module('properties')
  .controller('property', function($scope, $http) {
    var self = this;
    self.postcodeOutward = '';
    self.propertyTypeInputOne = '';
    self.propertyTypeInputTwo = '';
    self.postcode = function () {
      $http.get('/postcodemean?postcodeOutward=' + self.postcodeOutward)
        .then(function(response) {
          self.postcodeOutput = response.data;
      });
    };

    self.averagePropertyType = function() {
      var propertyTypeAverageOne;
      var propertyTypeAverageTwo;
      $http.get('averagedifference?propertyType=' + self.propertyTypeInputOne)
        .then(function(response) {
          propertyTypeAverageOne = response.data;
          $http.get('averagedifference?propertyType=' + self.propertyTypeInputTwo)
            .then(function(response) {
              propertyTypeAverageTwo = response.data;
              self.differenceOutput = propertyTypeAverageTwo.averagePrice - propertyTypeAverageOne.averagePrice
            });
        });


    };

    self.mostExpensive = function() {
      $http.get('topmostexpensive?percent=' + self.percent)
        .then(function(response) {
          self.expensiveProperties = response.data
      })
    }
  });
