<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="login_css.css">
 
    </head>
    <body>
        <div class="container">
            <h2>Login to product database</h2>
            <form action="login2.php" method="post">
      		  <?php if (isset($_GET['error'])) { ?>

           		 <p class="error"><?php echo $_GET['error']; ?></p>

       		 <?php } ?>

              <input type="text" name="uname" id="username" class="textcss" placeholder="username" />
              <input type="password" name="password" id="password" class="textcss"  placeholder="password" />   
              <button class="button-9" role="button" type="submit">Log in</button>
            </form>
          </div>
          </body>
</html> 



