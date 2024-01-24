
function showContent(sectionId) {
        // Hide all content sections
        var contentSections = document.querySelectorAll('#content > div');
            contentSections.forEach(function (section) {
                section.style.display = 'none';
        });

        // Show the selected content section
        var selectedSection = document.getElementById(sectionId);
        if (selectedSection) {
            selectedSection.style.display = 'block';
            //store the currently selected section
            localStorage.setItem('lastSelectedSection', sectionId);
        }
    }

function showStoredContent() {
        var storedSectionId = localStorage.getItem('lastSelectedSection');
        if (storedSectionId) {
            showContent(storedSectionId);
        } else {
            // Default to showing 'manage' section if nothing is stored
            showContent('manage');
        }
}

document.addEventListener('DOMContentLoaded', function() {
// Get data from the table


    var balance = document.getElementById('balance');
    var balanceValue = parseFloat(balance.innerText);
        var currentDataScope = false;
        var table = document.getElementById('data-table-tr');

        var originalTableState = document.getElementById('data-table-tr').innerHTML;

        var labels = new Set();
        var data = [];

        var currentLabels = [];
        var currentData = [];






        for (var i = 1; i < table.rows.length; i++) {
                labels.add(table.rows[i].cells[2].innerText);
            }

            for (var i = 0; i < labels.size; i++) {
                data.push(0);
            }
            //i really dislike javaScript
            //same fuck javaScript ~Jakub
            for (var i = 1; i < table.rows.length; i++) {
                var category = table.rows[i].cells[2].innerText;
                var value = parseFloat(table.rows[i].cells[1].innerText);
                var index = Array.from(labels).indexOf(category);
                if(value > 0){
                    data[index] += value;
                }
            }
            for (var i = 0; i < labels.size; i++) {
                if(data[i] != 0){
                    currentLabels.push(Array.from(labels)[i]);
                    currentData.push(data[i]);
                }
            }
            // Render the pie chart
            var canvas = document.getElementById('myCategoryChart');
            var ctx = canvas.getContext('2d');
            var myCategoryChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: currentLabels,
                    datasets: [{
                        label: 'Transfers',
                        data: currentData,
                        backgroundColor: [
                            "rgba(255, 99, 132, 0.8)",
                            "rgba(219, 112, 147, 0.8)",
                            "rgba(184, 125, 161, 0.8)",
                            "rgba(149, 137, 175, 0.8)",
                            "rgba(114, 150, 190, 0.8)",
                            "rgba(79, 162, 204, 0.8)"
                        ],
                        color: 'rgba(255, 255, 255, 1)',
                        borderColor: 'rgba(255, 255, 255, 1)', // Border color
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            labels: {
                                color: 'white'
                            }
                        }
                    }
                }

            });


    var currentLabels2 = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December"];
    var currentData2 = Array(12).fill(0);

    //i really dislike javaScript
    //same fuck javaScript ~Jakub

    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    for (var i = 1; i < table.rows.length; i++) {
        var dataYear = parseInt(table.rows[i].cells[4].innerText.split("-")[0]);

        if (currentYear !== dataYear) continue;
        var dataMonth = parseInt(table.rows[i].cells[4].innerText.split("-")[1]);;
        var value = parseFloat(table.rows[i].cells[1].innerText);
            currentData2[dataMonth - 1] += value;
    }

    // Render the pie chart
    var canvas2 = document.getElementById('myBalanceChart');
    var ctx2 = canvas2.getContext('2d');
    var myCategoryChart2 = new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: currentLabels2,
            datasets: [{
                label: 'Expenses each month this year',
                data: currentData2,
                backgroundColor: currentData2.map(value1 => (value1 > balanceValue) ? '#89fffd' : '#ef32d9'),
                color: 'rgba(255, 255, 255, 1)',
                borderColor: 'rgba(255, 255, 255, 1)', // Border color
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    labels: {
                        color: 'white'
                    }
                }
            },
            scales: {
                x: {
                    beginAtZero: true,
                    ticks: {
                        color: 'white' // Y-axis category label color
                    },

                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        color: 'white' // Y-axis category label color
                    },

                },
            }

        }

    });

            //listeners section
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

            var toggleChartButtonDos = document.getElementById('SwitchChartButton');

            toggleChartButtonDos.addEventListener('click', function () {
                if(!currentDataScope){
                    myCategoryChart.data = getNeededData(labels, -1);
                    currentDataScope = !currentDataScope;
                }else{
                    myCategoryChart.data = getNeededData(labels, 1);
                    currentDataScope = !currentDataScope;
                }
                // Update the chart
                myCategoryChart.update();
            });


            //functions section
             function getDefaultOptions(chartType) {
                if (chartType === 'pie') {
                    return {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                labels: {
                                    color: 'white'
                                }
                            }
                        }
                    };
                } else if (chartType === 'bar') {
                    return {
                        //you could put the code for changing the data format here
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                labels: {
                                    color: 'white'
                                }
                            }
                        },
                        scales: {
                            x: {
                                beginAtZero: true,
                                 ticks: {
                                    color: 'white' // Y-axis category label color
                                }
                            },
                            y: {
                                beginAtZero: true,
                                 ticks: {
                                    color: 'white' // Y-axis category label color
                                }
                            },
                        },
                    };
                }
                // We can also change the data and so on using methods like this
                // Add more options as needed for other chart types
                return {};
            }

            function getNeededData(labels, positive) {
                    var newData = [];
                    var newLabels = [];
                    var newCurrentData = [];
                    for (var i = 0; i < labels.size; i++) {
                        newData.push(0);
                    }
                    for (var i = 1; i < table.rows.length; i++) {
                        var category = table.rows[i].cells[2].innerText;
                        var value = parseFloat(table.rows[i].cells[1].innerText);
                        var index = Array.from(labels).indexOf(category);
                        if(value * positive > 0){
                            newData[index] += value;
                        }
                    }
                    for (var i = 0; i < labels.size; i++) {
                        if(newData[i] != 0){
                            newLabels.push(Array.from(labels)[i]);
                            newCurrentData.push(newData[i])
                        }
                    }
                    return {
                        labels: newLabels,
                        datasets: [{
                            label: 'Transfers',
                            data: newCurrentData,
                            backgroundColor: [
                                "rgba(255, 99, 132, 0.8)",
                                "rgba(219, 112, 147, 0.8)",
                                "rgba(184, 125, 161, 0.8)",
                                "rgba(149, 137, 175, 0.8)",
                                "rgba(114, 150, 190, 0.8)",
                                "rgba(79, 162, 204, 0.8)"
                            ],
                            color: 'rgba(255, 255, 255, 1)',
                            borderColor: 'rgba(255, 255, 255, 1)', // Border color
                            borderWidth: 1
                        }]
                    };
                }

    var dropdown = document.getElementById('custom-dropdown');
    dropdown.addEventListener('click', toggleDropdown);

    var dropdownOptions = document.getElementById("dropdownOptions");
    dropdownOptions.style.display = "none";

    var options = document.querySelectorAll(".custom-dropdown-option");
    options.forEach(function(option) {
        option.addEventListener('click', function() {
            selectOption(option.innerHTML);
        });
    });
    function toggleDropdown() {
        dropdownOptions.style.display = (dropdownOptions.style.display === "block") ? "none" : "block";
    }

    function selectOption(option) {
        var dropdown = document.querySelector('.custom-dropdown');
        dropdown.innerHTML = option;
        toggleDropdown();

        var tableToRemove = [];

        document.getElementById('data-table-tr').innerHTML = originalTableState;


        switch (option.toString()) {
            case "Monthly":
                for (i = 1; i < table.rows.length; i++) {
                    if (table.rows[i].cells[3].innerText !== 'Yes') {
                        tableToRemove.push(i);
                    }
                }
                break;
            case "Current month":
                var currentDate = new Date();
                var currentYear = currentDate.getFullYear();
                var month = currentDate.getMonth() + 1;
                for (i = 1; i < table.rows.length; i++) {
                    var dataYear = parseInt(table.rows[i].cells[4].innerText.split("-")[0]);
                    var dataMonth = parseInt(table.rows[i].cells[4].innerText.split("-")[1]);
                    if (dataMonth !== month || currentYear !== dataYear) {
                        tableToRemove.push(i);
                    }
                }
                break;
            case "Current year":
                var currentDate = new Date();
                var currentYear = currentDate.getFullYear();
                for (i = 1; i < table.rows.length; i++) {
                    var dataYear = parseInt(table.rows[i].cells[4].innerText.split("-")[0]);
                    if (dataYear !== currentYear) {
                        tableToRemove.push(i);
                    }
                }
                break;
            default:
                break;
        }

        // Remove rows from the table
        for (i = tableToRemove.length - 1; i >= 0; i--) {
            table.deleteRow(tableToRemove[i]);
        }

        if(currentDataScope){
            myCategoryChart.data = getNeededData(labels, -1);
        }else{
            myCategoryChart.data = getNeededData(labels, 1);
        }
        myCategoryChart.update();

    }
});

