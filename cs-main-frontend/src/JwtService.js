export function decodeToken(token) {
    if (token != null) {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }
}
export function rolesFromToken(token) {
    return decodeToken(token).roles;
}
export function role(roleName) {
    const roles = rolesFromToken(localStorage.getItem('token'));
    return !!roles.some(role => role.name === `ROLE_${roleName.toUpperCase()}`);
}