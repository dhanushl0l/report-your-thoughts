#  Report Your Thoughts

Live at: [https://thoughts.dhanu.cloud](https://thoughts.dhanu.cloud)  

##  About

This is a simple web app to **store**, **view**, and **edit** your thoughts in a MySQL database.  
No login required.  
You can also **delete** entries and check server **health** via hidden endpoints.

---

##  Project Structure

- **Main app logic**:  
  `src/main/java/cloud/shanu/dhanu/`

- **Database & other configs**:  
  `src/main/resources/application.properties`

- **Frontend (HTML, JS, CSS)**:  
  `src/main/resources/static`

Other files are not important unless you're a Java nerd.

---

## Running the Project

**Requirements**:  
- Docker  
- Docker Compose

**Run with:**

```bash
sudo docker compose up --build
