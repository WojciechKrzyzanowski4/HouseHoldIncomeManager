document.addEventListener('DOMContentLoaded', function () {
    // Get data from the table
    var table = document.getElementById('data-table');
    var labels = new Set();
    var data = [];

    for (var i = 1; i < table.rows.length; i++) {
        labels.add(table.rows[i].cells[2].innerText);
    }

    for (var i = 0; i < labels.size; i++) {
        data.push(0);
    }
    //i really dislike javaScript
    for (var i = 1; i < table.rows.length; i++) {
        var category = table.rows[i].cells[2].innerText;
        var value = parseFloat(table.rows[i].cells[1].innerText);
        var index = Array.from(labels).indexOf(category);
        data[index] += value;
    }

    console.log(data);

    // Render the pie chart
    var canvas = document.getElementById('myCategoryChart');
    var ctx = canvas.getContext('2d');
    var myCategoryChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: Array.from(labels),
            datasets: [{
                label: 'Values',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.8)',
                    'rgba(54, 162, 235, 0.8)',
                    'rgba(255, 206, 86, 0.8)',
                    // Add more colors as needed
                ],
                borderColor: 'rgba(255, 255, 255, 1)', // Border color
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
        }
    });

    // Get the button element
    var toggleChartButton = document.getElementById('toggleChartButton');

    // Add a click event listener to the button
    toggleChartButton.addEventListener('click', function () {
        // Toggle between different chart types
        var currentType = myCategoryChart.config.type;
        var newType = currentType === 'bar' ? 'pie' : 'bar';

        // Update the chart type
        myCategoryChart.config.type = newType;

        myCategoryChart.options = getDefaultOptions(newType);
        // Update the chart
        myCategoryChart.update();
    });
    //this sets the different styles of the chart depending of the button toggle
     function getDefaultOptions(chartType) {
            if (chartType === 'pie') {
                return {
                    responsive: true,
                    maintainAspectRatio: false,
                };
            } else if (chartType === 'bar') {
                return {

                    //you could put the code for changing the data format here
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        x: {
                            beginAtZero: true,
                        },
                        y: {
                            beginAtZero: true,
                        },
                    },
                };
            }
            // We can also change the data and so on using methods like this
            // Add more options as needed for other chart types
            return {};
        }
});