import React from "react";

export const Login = (data) => {
    console.log("Action: " + data.action);
    console.log("Method: " + data.method);
    return (<>
            <form id="loginForm" action={data.action} method={data.method}>
                <table className="sub-content rounded-corners inner-color login-box">
                    <tbody>
                    <tr>
                        <td><label htmlFor="username">Username:</label></td>
                        <td><input type="text" name="username"/></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="password">Password:</label></td>
                        <td><input type="password" name="password"/></td>
                    </tr>
                    <tr>
                        <td><br/></td>
                        <td><input type="submit"/></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </>
    )
}