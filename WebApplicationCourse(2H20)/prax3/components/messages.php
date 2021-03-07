<?php if($errors) { ?>
    <div class="alert error">
        <ul>
            <?php foreach($errors as $error) { ?>
                <li><?php echo $error; ?></li>
            <?php } ?>
        </ul>
    </div>
<?php } ?>
