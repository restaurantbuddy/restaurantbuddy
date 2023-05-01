export const ErrorPage = (props) => {
    return (<>
            <div className="sub-content rounded-corners innermost-color">
                <h1>{props.errorCode} - {props.details}</h1>
                <p>{props.description}</p>
            </div>
        </>
    )
}