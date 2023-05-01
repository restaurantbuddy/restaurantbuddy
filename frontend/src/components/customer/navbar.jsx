import Link from "next/link";

export const CustomerNavbar = () => {
    return (<>
            <div className="title-bar rounded-corners inner-color">
                <h1>RestaurantBuddy</h1>
                <p>A Fictitious Chinese Takeout Restaurant</p>
            </div>
            <nav className="inner">
                <ul className="rounded-corners inner-color">
                    <li><Link className="styled-link" href="/customer">Home</Link></li>
                    <li><Link className="styled-link" href="/customer/cart">Cart</Link></li>
                    <li><Link className="styled-link" href="/customer/about">About</Link></li>
                </ul>
            </nav>
        </>
    )
}
