async function checkSession() {
    return {hasGroup: false, hasUser: false}
}

function accessGroup(passCode) {
    return passCode === "alpha123" ? true : false
}

function accessUser(passCode) {
    return passCode === "bob"
}

function logOut() {
    console.log("Logged out")
}

export {checkSession, accessGroup, accessUser, logOut}