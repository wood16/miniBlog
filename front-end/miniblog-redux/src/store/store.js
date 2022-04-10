import { createStore , combineReducers, applyMiddleware} from "redux";
import { createLogger } from "redux-logger";
import home from "../reducers/HomeReducer";
import admin from "../reducers/AdminReducer";
import homeMiddleware from "../middleware/HomeMiddleware"
import adminMiddleware from "../middleware/AdminMiddleware";

const logger = createLogger()

export default createStore(
    combineReducers({
        home,
        admin
    }), 
    {}, 
    applyMiddleware(
        homeMiddleware,
        adminMiddleware,
        logger
    )
)