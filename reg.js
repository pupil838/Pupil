(function(){
    const config = {
        apiKey: "AIzaSyC46K-SwsRLciW-Me7g8HXu5jJlw-id3OE",
        authDomain: "pupil-3e3ed.firebaseapp.com",
        databaseURL: "https://pupil-3e3ed.firebaseio.com",
        projectId: "pupil-3e3ed",
        storageBucket: "pupil-3e3ed.appspot.com",
        messagingSenderId: "1012075073421"
    };

    firebase.initializeApp(config);
  //  const user_Email=document.getElementById('txtEmail');
   // const user_password=document.getElementById('txtPassword');
    const btnLogin=document.getElementById('submit');
    const fn=document.getElementById("first_name");
    const ln=document.getElementById("last_name");
    const rn=document.getElementById("Reg_no");
    const tel=document.getElementById("icon_telephone");
    const email=document.getElementById("email");
    const Addr=document.getElementById("per");
    const Addt=document.getElementById("tem");
    const ten_m=document.getElementById("ten");
    const twe_m=document.getElementById("twelve");
    const Grad=document.getElementById("grd");
    const Diploma=document.getElementById("dpa");
    const Current_location=document.getElementById("cl");
    const Prefferd_location=document.getElementById("pl");
    const file_ulp=document.getElementById("imgInp");



    btnLogin.addEventListener('click',e=>{
        const email=txtEmail.value;
        const pass=txtPass.value;
        const auth=firebase.auth();
        const promise =auth.signInWithEmailAndPassword(email,pass);
        promise.catch(e=> console.log(e.message));
    });
    firebase.auth().onAuthStateChanged(firebaseuser =>{
        if(firebaseuser){
            var userId = firebaseuser.uid;
            firebase.database().ref('/caremedusers/' + userId).once('value').then(function (snapshot) {
                var occu = snapshot.val().occupation;
                if (occu == "Doctor") {
                    alert("Doctor Logged IN");

                    location.href = "doctorhome.html";

                } else if (occu == "Patient") {
                    alert("Patient Logged IN");
                    location.href = "patienthome.html";
                } else if (occu == "Attendant") {
                    alert("Attendant Logged IN");
                    location.href = "attendanthome.html";
                } else {
                    firebase.auth().signOut();
                }
                // ...
            });
        }else{
            alert("User Logged OUT");
        }
    });
    btnLogout.addEventListener('click', e => {
        firebase.auth().signOut();
    });
    regtxtname.addEventListener('click', e => {
        location.href = "login.html";
        firebase.auth().signOut();

    });


}());