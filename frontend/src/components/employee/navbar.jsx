import Link from "next/link";

export const EmployeeNavbar = () => {
    return (<>
            <div className="title-bar rounded-corners inner-color">
                <h1>Employee Portal</h1>
                <p>For Authorized Users Only</p>
            </div>
            <nav className="inner">
                <ul className="rounded-corners inner-color">
                    <li><Link className="styled-link" href="/employee">Home</Link></li>
                    <li><Link className="styled-link" href="/auth/logout">Logout</Link></li>
                </ul>
            </nav>
        </>
    )
}
