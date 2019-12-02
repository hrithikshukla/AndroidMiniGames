package com.example.game.Activities.main;

import android.widget.ImageView;

import com.example.game.R;

import java.util.ArrayList;
import java.util.List;

class ImageListCreator {
  // List of images to display
  private List<ImageView> images;
  // The activity that this class gets images from, needed in order to execute findViewById()
  private ShopActivity activity;

  /** @param activity the activity that created this imageListCreator */
  ImageListCreator(ShopActivity activity) {
    this.activity = activity;
    images = new ArrayList<>();
  }

  /** @return returns this builder object */
  ImageListCreator setImageList() {

    // Find and add images to images list and set ID tags and set character prices
    ImageView worm = activity.findViewById(R.id.char_worm);
    worm.setTag(R.id.num, R.drawable.char_worm);
    worm.setTag(R.id.price, 21);
    worm.setTag(R.id.id, R.id.char_worm);
    images.add(worm);

    // Rest of the code is the same as above but for different images
    ImageView bird = activity.findViewById(R.id.char_bird);
    bird.setTag(R.id.num, R.drawable.char_bird_blue);
    bird.setTag(R.id.price, 42);
    bird.setTag(R.id.id, R.id.char_bird);
    images.add(bird);
    ImageView spider = activity.findViewById(R.id.char_spider);
    spider.setTag(R.id.num, R.drawable.char_spider);
    spider.setTag(R.id.price, 50);
    spider.setTag(R.id.id, R.id.char_spider);
    images.add(spider);
    ImageView dog = activity.findViewById(R.id.char_dog);
    dog.setTag(R.id.num, R.drawable.char_dog);
    dog.setTag(R.id.price, 69);
    dog.setTag(R.id.id, R.id.char_dog);
    images.add(dog);
    ImageView tree = activity.findViewById(R.id.char_tree);
    tree.setTag(R.id.num, R.drawable.char_tree);
    tree.setTag(R.id.price, 90);
    tree.setTag(R.id.id, R.id.char_tree);
    images.add(tree);
    ImageView astro = activity.findViewById(R.id.char_astro);
    astro.setTag(R.id.num, R.drawable.char_astro);
    astro.setTag(R.id.price, 90);
    astro.setTag(R.id.id, R.id.char_astro);
    images.add(astro);
    ImageView alien = activity.findViewById(R.id.char_alien);
    alien.setTag(R.id.num, R.drawable.char_alien_dark);
    alien.setTag(R.id.price, 90);
    alien.setTag(R.id.id, R.id.char_alien);
    images.add(alien);
    ImageView monster = activity.findViewById(R.id.char_monster);
    monster.setTag(R.id.num, R.drawable.char_monster_red);
    monster.setTag(R.id.price, 101);
    monster.setTag(R.id.id, R.id.char_monster);
    images.add(monster);
    ImageView bat = activity.findViewById(R.id.char_bat);
    bat.setTag(R.id.num, R.drawable.char_bat);
    bat.setTag(R.id.price, 200);
    bat.setTag(R.id.id, R.id.char_bat);
    images.add(bat);
    ImageView king = activity.findViewById(R.id.char_king);
    king.setTag(R.id.num, R.drawable.char_king);
    king.setTag(R.id.price, 200);
    king.setTag(R.id.id, R.id.char_king);
    images.add(king);
    ImageView summoner = activity.findViewById(R.id.char_summoner);
    summoner.setTag(R.id.num, R.drawable.char_summoner);
    summoner.setTag(R.id.price, 5000);
    summoner.setTag(R.id.id, R.id.char_summoner);
    images.add(summoner);
    ImageView viking1 = activity.findViewById(R.id.char_viking1);
    viking1.setTag(R.id.num, R.drawable.char_viking_1);
    viking1.setTag(R.id.price, 250);
    viking1.setTag(R.id.id, R.id.char_viking1);
    images.add(viking1);
    ImageView viking2 = activity.findViewById(R.id.char_viking2);
    viking2.setTag(R.id.num, R.drawable.char_viking_2);
    viking2.setTag(R.id.price, 250);
    viking2.setTag(R.id.id, R.id.char_viking2);
    images.add(viking2);
    ImageView viking3 = activity.findViewById(R.id.char_viking3);
    viking3.setTag(R.id.num, R.drawable.char_viking_3);
    viking3.setTag(R.id.price, 250);
    viking3.setTag(R.id.id, R.id.char_viking3);
    images.add(viking3);
    ImageView wizard = activity.findViewById(R.id.char_wizard);
    wizard.setTag(R.id.price, 325);
    wizard.setTag(R.id.num, R.drawable.char_wizard);
    wizard.setTag(R.id.id, R.id.char_wizard);
    images.add(wizard);
    ImageView archer = activity.findViewById(R.id.char_archer);
    archer.setTag(R.id.num, R.drawable.char_archer);
    archer.setTag(R.id.price, 350);
    archer.setTag(R.id.id, R.id.char_archer);
    images.add(archer);
    ImageView knight = activity.findViewById(R.id.char_knight);
    knight.setTag(R.id.num, R.drawable.char_knight);
    knight.setTag(R.id.price, 375);
    knight.setTag(R.id.id, R.id.char_knight);
    images.add(knight);
    ImageView samurai = activity.findViewById(R.id.char_samurai);
    samurai.setTag(R.id.num, R.drawable.char_samurai);
    samurai.setTag(R.id.price, 420);
    samurai.setTag(R.id.id, R.id.char_samurai);
    images.add(samurai);
    ImageView shogun = activity.findViewById(R.id.char_shogun);
    shogun.setTag(R.id.num, R.drawable.char_shogun);
    shogun.setTag(R.id.price, 500);
    shogun.setTag(R.id.id, R.id.char_shogun);
    images.add(shogun);
    return this;
  }

  /** @return returns a list of images to display */
  List<ImageView> getImageList() {
    return images;
  }
}
