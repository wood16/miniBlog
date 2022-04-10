import './App.css';
import { BrowserRouter as Router, NavLink, Redirect, Route, Switch } from 'react-router-dom';
import Menu from './component/menu/Menu';
import Home from './component/homepage/Home';
import Detail from './component/homepage/detailpage/Detail';
import About from './component/aboutpage/About';
import Contact from './component/contactpage/Contact';
import Administration from './component/administration/Administration';
import DetailBlog from './component/administration/detail/DetailBlog';
import Author from './component/author/Author';

const App = () => {
  return (
    <div className="App">
       <Router>
        <div className="menu">
          <Menu />
        </div>
        <div className='container'>
          <div className="row">
              <div className="col">
                  <Author/>
              </div>
              <div className="col-9">
                  <Switch>
                  <Route path="/" exact>
                    <Redirect to="/home"/>
                  </Route>
                  <Route path="/home" exact component={Home}/>
                  <Route path="/books/:id" exact component={Detail}/>
                  <Route path="/about" component={About}/>
                  <Route path="/contact" component={Contact}/>

                  <Route path="/admin" exact component={Administration}/>
                  <Route path="/admin/books/:id" exact component={DetailBlog} /> 
                  <Route path="/admin/add" exact component={DetailBlog} /> 
                  </Switch>
              </div>
          </div>
        </div>
      </Router>
    </div>
  );
}

export default App;
