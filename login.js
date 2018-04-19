(function(){
    var config = {
    apiKey: "AIzaSyC46K-SwsRLciW-Me7g8HXu5jJlw-id3OE",
    authDomain: "pupil-3e3ed.firebaseapp.com",
    databaseURL: "https://pupil-3e3ed.firebaseio.com",
    projectId: "pupil-3e3ed",
    storageBucket: "pupil-3e3ed.appspot.com",
    messagingSenderId: "1012075073421"
    };
    
    firebase.initializeApp(config);
    var user_Email=document.getElementById('txtEmail');
    var user_password=document.getElementById('txtPassword');
    var btnLogin=document.getElementById('loginButton');
    
    btnLogin.addEventListener('click',e=>{
        var email=user_Email.value;
        var pass=user_password.value;
        var auth=firebase.auth();
        var promise =auth.signInWithEmailAndPassword(email,pass);
        promise.catch(e=> console.log(e.message));
    });

    firebase.auth().onAuthStateChanged(firebaseuser =>{
        if(firebaseuser){
           alert("User Logged IN");

            //location.href = "dashboard.html";
       }else{
           alert("User Logged OUT");
       }
    });
    
}());