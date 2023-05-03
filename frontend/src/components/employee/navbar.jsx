import Link from "next/link";

export const EmployeeNavbar = (data) => {
    return (<>
            <div className="title-bar rounded-corners inner-color">
                <h1>{data.restaurantName} - Employee Portal</h1>
                <p>For Authorized Users Only</p>
            </div>
            <nav className="inner">
                <ul className="rounded-corners inner-color">
                    <li><Link className="styled-link" href="/employee">Overview</Link></li>
                    <li><Link className="styled-link" href="/employee/order-management">Order Management</Link></li>
                    <li><Link className="styled-link" href="/auth/logout">Logout</Link></li>
                </ul>
            </nav>
        </>
    )
}
