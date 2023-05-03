import Head from "next/head";
import {Header} from "@/src/components/header";
import {Footer} from "@/src/components/shared/footer";
import {OwnerNavbar} from "@/src/components/owner/navbar";

export default function Page() {
    return (<>
        <Head>
            <title>Owner - User Management</title>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="icon" href="/favicon.ico"/>
        </Head>
        <div className="main-content">
            <Header/>
            <OwnerNavbar restaurantName="RestaurantBuddy"/>

            <div className="rounded-corners inner-color">
                <h1>User Management</h1>
            </div>

            <Footer/>
        </div>
    </>)
}
