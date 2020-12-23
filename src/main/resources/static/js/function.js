

function setModal(room_id, date, start, end ){
    document.getElementById("show-id").value = room_id;
    document.getElementById("show-date").value = date;
    document.getElementById("show-duration").value = start + ' - ' + end;

    document.getElementById("room_id").value = room_id;
    document.getElementById("date").value = date;
    document.getElementById("start").value = start;
    document.getElementById("end").value = end;
}

function handleChange(){
    let date = document.getElementById("picker").value
<<<<<<< HEAD
    let username = document.getElementById("username").value
    window.location.href = "http://localhost:8092/booking/" + username + "&" + date + "&1"
=======
    window.location.href = "http://localhost:8092/booking/" + date + "&1"
>>>>>>> f12d0b25180366ea40c8aaba1d47e45b15880ccd
}


