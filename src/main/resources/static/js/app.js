let roleList = [
    {id: 1, role: "ROLE_USER"},
    {id: 2, role: "ROLE_ADMIN"}
]

$(async function () {
    await infoUser();
    await getUsers();
    await getNewUserForm();
    await getDefaultModal();
    await createUser();

})


const userFetch = {
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async () => await fetch('api/users'),
    findUserByUsername: async () => await fetch(`api/users/user`),
    findOneUser: async (id) => await fetch(`api/users/${id}`),
    addNewUser: async (user) => await fetch('api/users', {
        method: 'POST',
        headers: userFetch.headers,
        body: JSON.stringify(user)
    }),
    updateUser: async (user) => await fetch(`api/users/`, {
        method: 'PUT',
        headers: userFetch.headers,
        body: JSON.stringify(user)
    }),
    deleteUser: async (id) => await fetch(`api/users/${id}`, {method: 'DELETE', headers: userFetch.head})
}

async function infoUser() {
    let temp = '';
    const info = document.querySelector('#info');
    await userFetch.findUserByUsername()
        .then(res => res.json())
        .then(user => {
            temp += `
            <strong class="text-white">${user.email}</strong>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link disabled text-white">
                            with roles ${user.roles.map(role => " " + role.name.substring(5))}
                        </a>
                    </li>
                </ul>
             </div>
             <form class="form-inline" action="/logout" method="post">
                <a href="#" class="nav-text nav-link text-white-50 active"
                    onclick="this.closest('form').submit();return false;" role="button">Logout</a>
                <input type="submit" name="target" hidden/>
    </form>
            `
        });
    info.innerHTML = temp;
}