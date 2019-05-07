const ACCESS_TOKEN="UOHJI4SSJ4C3AFIUDBTXX5TFGMWC3KQH";
const WIT_ENDPOINT_SPEECH="https://api.wit.ai/speech";

export default {
    async getIntentFromAudio(file) {
        try {
            let form = new FormData();
            form.append('file', file);
            let response = await fetch('http://10.0.2.2:5000/wit', {
                method: "POST",
                body: form,
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            });
            let responseData = await response.json();
    
            return responseData;
        } catch(e) {
            console.log(e);
        }
    }
}