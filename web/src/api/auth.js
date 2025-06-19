async function checkSession() {
    return {hasGroup: false, hasUser: false}
}

function accessGroup(passCode) {
    return passCode === "alpha123" ? true : false
}

function accessUser(passCode) {
    return passCode === "123"
}

export {checkSession, accessGroup, accessUser}