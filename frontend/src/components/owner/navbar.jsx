import Link from "next/link";

export const OwnerNavbar = (data) => {
    return (<>
            <div className="title-bar rounded-corners inner-color">
                <h1>{data.restaurantName} - Owner Portal</h1>
                <p>For Authorized Users Only</p>
            </div>
            <nav className="inner">
                <ul className="rounded-corners inner-color">
                    <li><Link className="styled-link" href="/owner">Overview</Link></li>
                    <li><Link className="styled-link" href="/owner/user-management">User Management</Link></li>
                    <li><Link className="styled-link" href="/owner/menu-management">Menu Management</Link></li>
                    <li><Link className="styled-link" href="/auth/logout">Logout</Link></li>
                </ul>
            </nav>
        </>
    )
}
