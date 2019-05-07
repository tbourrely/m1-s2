export default {
    /**
     * Send the file to the StreamingServersManager throught it's API
     * The manager will in it's turn contact WIT.AI and return the response to the app
     * 
     * @param {Object} file 
     */
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