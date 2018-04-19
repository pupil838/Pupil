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
    var Vname=document.getElementById("Vname");
     var nameref=firebase.database().ref("student_reg/name");
     nameref.once("value").function(datasnapshot){
     vname.innerText=datasnapshot.child("name").val();
	         // Vname.innerText=datasnapshot.val();
});
    
}());



//name=dataSnapshot.child("1").child("name").getValue(String.class).toString() ;