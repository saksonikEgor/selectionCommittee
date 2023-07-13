let sum = 0;
let enrolleeIndex = 0;
let achievementIndex = 0;
let achievementSum = 0;

function getResultSum() {
    for (let i = 0; i < achievementIndex; i++) {
        let curBonus = document
            .getElementById('achievement' + enrolleeIndex + String(i))
            .innerHTML;

        if (curBonus !== '-')
            achievementSum += Number(curBonus);
    }

    let testResult;
    if (document.getElementById('testResult' + enrolleeIndex) == null)
        testResult = 0;
    else
        testResult = Number(document.getElementById('testResult' + enrolleeIndex).innerHTML);


    document.getElementById('sumResult' + enrolleeIndex).innerHTML =
        Number(document.getElementById('firstSubject' + enrolleeIndex).innerHTML) +
        Number(document.getElementById('secondSubject' + enrolleeIndex).innerHTML) +
        Number(document.getElementById('thirdSubject' + enrolleeIndex).innerHTML) +
        testResult +
        achievementSum;
}

function updateVariables() {
    sum = 0;
    enrolleeIndex++;
    achievementIndex = 0;
    achievementSum = 0;
}

function sortTable(n) {
    let table, rows, switching, i, x, y, shouldSwitch, dir, switchCount = 0;
    table = document.getElementById("enrolleeTable");
    switching = true;
    dir = "asc";

    while (switching) {
        switching = false;
        rows = table.getElementsByTagName("TR");

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];

            if (dir === "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchCount++;
        } else {
            if (switchCount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}