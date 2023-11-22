document.getElementById("addNewDirectorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    newInput.id=`Director${count}`;
    newInput.classList.add("generatedInput");
    container.append(newInput);
});

document.getElementById("addNewActorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    newInput.id=`Actor${count}`;
    newInput.classList.add("generatedInput");
    container.append(newInput);
});

document.getElementById("addNewFactInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('textarea');
    const count = inputs.length;
    let newInput = document.createElement("textarea");
    newInput.id=`Fact${count}`;
    
    container.append(newInput);
});

document.getElementById('logout').addEventListener('click',(e)=>{
    e.preventDefault();
})