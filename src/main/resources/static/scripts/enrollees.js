let rowsNumber = 0;
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

    document.getElementById('sumResult' + enrolleeIndex).innerHTML =
        Number(document.getElementById('firstSubject' + enrolleeIndex).innerHTML) +
        Number(document.getElementById('secondSubject' + enrolleeIndex).innerHTML) +
        Number(document.getElementById('thirdSubject' + enrolleeIndex).innerHTML) +
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
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir === "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchCount ++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchCount === 0 && dir === "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}