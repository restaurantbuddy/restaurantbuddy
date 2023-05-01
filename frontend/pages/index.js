import Head from 'next/head'
import {CustomerHeader} from "@/src/components/customer/header";

export default function Home() {
    return (<>
            <Head>
                <title>RestaurantBuddy - A Full-Stack Web Application for Managing Restaurant Orders</title>
                <meta name="description"
                      content="RestaurantBuddy - A Full-Stack Web Application for Managing Restaurant Orders"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="icon" href="/favicon.ico"/>
            </Head>
            <CustomerHeader/>
        </>
    )
}
