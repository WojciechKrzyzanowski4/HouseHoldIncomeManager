document.addEventListener('DOMContentLoaded', function() {
    // Get data from the table
    var table = document.getElementById('data-table');
    var labels = [];
    var data = [];

    for (var i = 1; i < table.rows.length; i++) {
    labels.push(table.rows[i].cells[1].innerText);
    data.push(parseInt(table.rows[i].cells[0].innerText));
    }
    var canvas = document.getElementById('myChart');
    var ctx = canvas.getContext('2d');
    var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: labels,
        datasets: [{
        label: 'Values',
        data: data,
        backgroundColor: 'rgba(75, 192, 192, 0.6)',
        borderColor: 'rgba(75, 192, 192, 1)',
        borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            },
            x: {
                beginAtZero: true
            }
            }
    }
    });
});