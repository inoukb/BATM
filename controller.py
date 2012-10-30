import webapp2
import jinja2
import os

jinja_environment = jinja2.Environment(
    loader=jinja2.FileSystemLoader(os.path.dirname(__file__)))

class PhotoApi(webapp2.RequestHandler):
    def get(self):
        self.response.headers['Content-Type'] = 'application/json'
        self.response.write('[]')

class HomePage(webapp2.RequestHandler):
    def get(self):
        template_values = {
            'title': 'Bikes Against The Machine',
            'page' : 'home',
            'content': open(os.path.dirname(__file__) + '/part_home.html', 'r').read(),
        }

        template = jinja_environment.get_template('template.html')
        self.response.headers['Content-Type'] = 'text/html'
        self.response.out.write(template.render(template_values))

class GoodiesPage(webapp2.RequestHandler):
    def get(self):
        template_values = {
            'title': 'Bikes Against The Machine - Goodies',
            'page' : 'goodies',
            'content': open(os.path.dirname(__file__) + '/part_goodies.html', 'r').read(),
        }

        template = jinja_environment.get_template('template.html')
        self.response.headers['Content-Type'] = 'text/html'
        self.response.out.write(template.render(template_values))

class AboutPage(webapp2.RequestHandler):
    def get(self):
        template_values = {
            'title': 'Bikes Against The Machine - About',
            'page' : 'about',
            'content': open(os.path.dirname(__file__) + '/part_about.html', 'r').read(),
        }

        template = jinja_environment.get_template('template.html')
        self.response.headers['Content-Type'] = 'text/html'
        self.response.out.write(template.render(template_values))

app = webapp2.WSGIApplication([('/api/photo', PhotoApi), ('/', HomePage), ('/goodies', GoodiesPage), ('/about', AboutPage)],
                              debug=True)
