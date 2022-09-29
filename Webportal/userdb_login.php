<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" type="text/css" href="login_css.css">
 
    </head>
    <body>
        <div class="container">
            <h2>Login to user database</h2>

            <form action="login.php" method="post">
       		 <?php if (isset($_GET['error'])) { ?>

           		 <p class="error"><?php echo $_GET['error']; ?></p>

       		 <?php } ?>
              <input type="text" name="uname" title="username" class="textcss"  placeholder="username" />
              <input type="password" name="password" title="username" class="textcss"  placeholder="password" />
              
              <button class="button-9" type="submit" role="button">Log in</button>
            </form>
          </div>
          </body>
</html> 





