<template>
    <div class="card mb-5 col-lg-3 jm-music-card">
        <img src="../assets/pictures/erik-mclean-9y1cTVKe1IY-unsplash.jpg" class="card-img-top" alt="Image">
        <div class="card-body">
            <h5 class="card-title title-2">{{ $t('musicTitle') }}</h5>
            <ul class="card-txt txt-body">
                <li class="m-1 theme-secondary">{{ $t('albumName') }}</li>
                <li class="m-1 theme-tertiary">{{ $t('groupName') }}</li>
            </ul>

            <div class="d-flex align-items-center justify-content-evenly">
                <progress class="progress col-8" id="customProgressBar" value="50" max="100">
                </progress>
                <button class="btn p-2">
                    <i class="bi bi-play-btn text-primary" style="font-size: 2rem;"></i>
                    <span class="visually-hidden">{{ $t('play') }}</span>
                </button>
            </div>
            <!-- <time datetime="PT1M15S">2:07</time> / <time datetime="PT3M45S">3:35</time> -->
        </div>
    </div>
</template>