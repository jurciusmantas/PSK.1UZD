function onExistingAddDataChange(originalValue, index){
    let inputElement = document.getElementById(`add-data-input-${index}`);
    let tbody = document.getElementsByTagName('tbody')[0];
    let tr = tbody.children[index];
    let td = tr.lastElementChild;

    if (inputElement.value !== originalValue)
        td.style.setProperty('visibility', 'visible');

    else if (td.style.visibility == 'visible')
        td.style.setProperty('visibility', 'hidden');
}

function getVars(index){
    let inputElement = document.getElementById(`add-data-input-${index}`);
    console.log("newValue - ", inputElement.value);
    let newValueElement = document.getElementById(`ih-newvalue-value-${index}`);
    console.log(newValueElement);
    let newValueIndex = document.getElementById(`ih-newvalue-index-${index}`);
    console.log(newValueIndex);
}