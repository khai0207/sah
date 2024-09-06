--v3
--[[
morenbuou
1
0
penxue
2
{default,qianjin,0,0,0,4,1.000000}{default,gongji6,1,0,5,81,1.000000}
1
{default,0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}
0

0

1
{0,morenbuou,2,0,0.000000,0.000000,80,0}
2
{delay,,0,0,0,3,0.000000}{default,texiao1,1,0,4,80,1.000000}
2
{delay,0.000000,0.000000,0.000000,0.000000,0,0,0,3,0.000000,0,0,0,0}{default,0.000000,0.000000,-200.000000,0.000000,0,1,4,5,0.000000,0,0,0,0}
1
{default,0.500000,0.500000,0.500000,0.500000,0,0,1}
0


daiji
]]--
local skillTest = {
attack_type       = "yuangong",
start_p           = {2,0,0},
end_p             = {2,0,0},
anim_sequence    = {{"qianjin",1.000000,0,4,0},{"gongji6",1.000000,5,81,1}}, 
pos_sequence      = {{0.000000,0.000000,-200.000000,0.000000,0,1,0,4,0.000000,0,0,0,0}}, 
scale_sequence     = {}, 
fade_sequence      = {}, 
other_sequence      = {{"morenbuou",2,0,0.000000,0.000000,{{"",0.000000,0,3,0},{"texiao1",1.000000,4,80,1}},{{0.000000,0.000000,0.000000,0.000000,0,0,0,3,0.000000,0,0,0,0},{0.000000,0.000000,-200.000000,0.000000,0,1,4,5,0.000000,0,0,0,0}},{{0.500000,0.500000,0.500000,0.500000,0,0,1}},{},0}}, 
bloodNum          = 7,
flyAnim           = "ladeng",
flyAnim_equence   = {{"impact",1}}, 
par               = "",
fly_v             = 100,
fly_h             = 0,
hurtAnim          = "penxue",
hurtAnim_equence  = {{"impact",1}},
skillCallFunc     = function(self,battleSkill,battleTable) 
   local skillData = battleTable.m_currentFrameData; 
   table.foreach(skillData,function(k,v) 
   end); 
   local hpValue = 1;
   local function attackEnd()
   end
   battleSkill:createNormalAttack({skills_table = self,hpValue = skillData.hurt,animEndCallFunc = attackEnd});
end, 
}
return skillTest
