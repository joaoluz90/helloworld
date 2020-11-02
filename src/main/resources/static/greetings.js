window.onload = async function() {
    try {
        let res = await $.ajax( {
            url: "/api/java/tester/",
            method: "get",
            dataType: "text"
        });
        document.getElementById("message").innerHTML = res;
    } catch (error) { console.log(error); }
}

async function greeting() {
    try {
        let name = document.getElementById("name").value;
        let res = await $.ajax({
            url: "/api/java/tester/"+name,
            method: "get",
            dataType: "text"
        });
        document.getElementById("message").innerHTML = res;
    } catch (error) { console.log(error); }
}