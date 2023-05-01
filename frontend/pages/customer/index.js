import Head from "next/head";
import {CustomerNavbar} from "@/src/components/customer/navbar";
import {Footer} from "@/src/components/shared/footer";
import {Header} from "@/src/components/header";

export default function CustomerIndexPage() {
    return (<>
        <Head>
            <title>Customer - Home</title>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="icon" href="/favicon.ico"/>
        </Head>
        <div className="main-content">
            <Header/>
            <CustomerNavbar/>

            <div className="sub-content inner-color rounded-corners">
                <h1>Home</h1>
            </div>

            <Footer/>
        </div>
    </>)
}
