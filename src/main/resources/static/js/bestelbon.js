var checkboxes = document.querySelectorAll("input[type='checkbox']");
var submitButt = document.getElementById("afgewerktKnop");

// checking if all checkboxes are checked
function checkSubmitButton() {
    const aantalCheckboxes = checkboxes.length;
    var aantalChecked = 0;
    for (const checkbox of checkboxes) {
        if (checkbox.checked) {
            aantalChecked++;
        }
    }
    submitButt.disabled = aantalChecked !== aantalCheckboxes;
}

async function postCheckbox(checkbox) {
    const data = {
        magazijnplaatsid: checkbox.dataset.magazijnplaatsid,
        status: checkbox.checked
    }
    const response = await fetch("checkbox",
        {method: "POST",
            body:JSON.stringify(data),
            headers: {
                "Accept":"application/json",
                "Content-Type": "application/json"
            }
        });
}

checkSubmitButton();

for (const checkbox of checkboxes) {
    checkbox.onclick = async function () {
        checkSubmitButton();
        await postCheckbox(checkbox);
    }
}