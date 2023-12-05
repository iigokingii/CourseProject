function fileToByteArray(file){
    return new Promise((resolve,reject)=>{
        try{
            var reader = new FileReader();
            var fileBytes =[];
            reader.readAsArrayBuffer(file);
            reader.onloadend = function(e){
                if(e.target.readyState== FileReader.DONE){
                    var arrayBuffer = e.target.result;
                    var array = new Uint8Array(arrayBuffer);
                    for(byte of array){
                        fileBytes.push(byte);
                    }
                }
                resolve(fileBytes);
            }
        }
        catch(e){
            reject(e);
        }
    })
}

async function BlobToImage(blobDataString, container) {
    //const blobData = new Blob([blobDataString], { type : 'plain/text' });
    const blobData = await fetch(`data:plain/text;base64,${blobDataString}`).then(res => res.blob());
    var blob;
    if (blobData instanceof Blob || blobData instanceof File) {
      blob = blobData;
    } else {
      console.error('Invalid blobData: ' + blobData);
      return;
    }
  
    var imageUrl = URL.createObjectURL(blob);
    var imgElement = document.createElement('img');
    imgElement.src = imageUrl;
    imgElement.classList.add("default-avatar");

    container.appendChild(imgElement);
}

async function BlobToImageURL(blobDataString, container) {
    //const blobData = new Blob([blobDataString], { type : 'plain/text' });
    const blobData = await fetch(`data:plain/text;base64,${blobDataString}`).then(res => res.blob());
    var blob;
    if (blobData instanceof Blob || blobData instanceof File) {
      blob = blobData;
    } else {
      console.error('Invalid blobData: ' + blobData);
      return;
    }
  
    return URL.createObjectURL(blob);
     
    // var imgElement = document.createElement('img');
    // imgElement.src = imageUrl;
    // imgElement.classList.add("default-avatar");

    // container.appendChild(imgElement);
}




async function BlobToInputFile(blobDataString, inputContainer){
    
    const blobData = await fetch(`data:plain/text;base64,${blobDataString}`).then(res => res.blob());
    var blob;
    if (blobData instanceof Blob || blobData instanceof File) {
      blob = blobData;
    } else {
      console.error('Invalid blobData: ' + blobData);
      return;
    }

    const file = new File([blob], "previous.jpg", { type: "image/jpeg" });
    let container = new DataTransfer();
    container.items.add(file);
    inputContainer.files = container.files;
}