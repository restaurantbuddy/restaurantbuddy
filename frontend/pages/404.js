import Head from "next/head";
import {Footer} from "@/src/components/shared/footer";
import {HttpErrorMessage} from "@/src/components/shared/http-error-message";
import {Header} from "@/src/components/header";

export default function Home() {
    return (<>
        <Head>
            <title>404 - Not Found</title>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="icon" href="/favicon.ico"/>
        </Head>
        <div className="main-content">
            <Header/>
            <HttpErrorMessage errorCode="404" details="Not Found" description="The requested page could not be found!"/>
            <Footer/>
        </div>
    </>)
}
