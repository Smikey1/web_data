var xValues = ["Brazil","Italy","Germany","France","Argentina", "Uruguay","Spain", "England"];
var yValues = [5,4,3,1,3,2,1,1];
var barColors = [
  "#fcdc08",
  "#086404",
  "#000000",
  "#082454",
  "#2596be",
  "#5c68a4",
  "#b0141c",
  "#b80a0b"
];
new Chart("piechart", {
  type: "pie",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    title: {
      display: true,
      text: "World Cup Winners"
    }
  }
});

var yValues = [5,4,3,1,3,2,1,1,0 ] ;

new Chart("bargraph", {
  type: "bar",
  data: {
    labels: xValues,
    datasets: [{
      backgroundColor: barColors,
      data: yValues
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: "World Cup Winners"
    }
  }
});


new Chart("linegraph", {
  type: "line",
  data: {
    labels: xValues,
    datasets: [{
      data: [5,4,3,1,3,2,1,1,0 ],
      borderColor: "red",
      fill: false}
      ,
    //{
    //   data: [1,2,3,4,5,6,7,8,9,10],
    //   borderColor: "green",
    //   fill: false
    // }, {
    //   data: [1,2,3,4,5,6,7,8,9,10],
    //   borderColor: "blue",
    //   fill: false
    // }
      ]
  },
  options: {
    legend: {display: false}
  }
});